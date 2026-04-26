#include <ESP8266WiFi.h>
#include <WiFiClient.h>
#include <PubSubClient.h>
#include <DHT.h>
#include "arduino_secrets.h"

const char* ssid = SECRET_SSID;
const char* password = SECRET_PASS;

const char* mqtt_broker = MQTT_BROKER;
const char* topic = MQTT_WEATHER_TOPIC;
const int mqtt_port = MQTT_PORT;
const unsigned long ONE_MIN = 1000 * 60;
const unsigned long FIVE_MINS = ONE_MIN * 5;

WiFiClient espClient;
PubSubClient client(espClient);

#define DHTTYPE DHT11
#define DHTPIN 13
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);
  connectToWifi();
  connectToMqtt();
  dht.begin();
}

void connectToWifi() {
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.printf("\nConnected to %s: ", ssid);
  Serial.printf("IP address: %s\n", WiFi.localIP());
}

// TODO: Secure MQTT communications
void connectToMqtt() {
  Serial.println("Connecting to MQTT...");
  client.setServer(mqtt_broker, mqtt_port);
  while (!client.connected()) {
    String client_id = "WeMos D1 Temperature Logger";
    client_id += String(WiFi.macAddress());
    Serial.printf("Client %s connecting to broker.\n", client_id.c_str());
    if (client.connect(client_id.c_str())) {
      Serial.println("Connected to MQTT broker");
      delay(200);
    } else {
      Serial.printf("Failed with state: %s\n", client.state());
      delay(2000);
    }
  }
}

void loop() {
  Serial.println("Device is running");
  delay(60000); // 1 min delay
  
  // Read from DHT
  float humidity = dht.readHumidity();
  float temperature = round(dht.readTemperature() * 10.0) / 10.0;
  if (isnan(humidity) || isnan(temperature)) {
    Serial.println("Failed to read from DHT sensor");
  }
  
  client.loop();

  // Send to MQTT
  if (client.connected()) {
    Serial.printf("Publishing to %s\n", topic);
    char payload[50];
    snprintf(payload, sizeof(payload), "{\"temperature\": \"%f\", \"humidity\": \"%f\"}", round(temperature * 10.0) / 10.0, humidity);
    client.publish(topic, payload);
    Serial.print("Sent data: ");
    Serial.println(payload);
  } else {
    Serial.println("Disconnected from MQTT broker");
    connectToMqtt();

    // TODO: Fix disconnection from extended wait / connect only when send
    Serial.printf("Retry publishing to %s\n", topic);
    char payload[60];
    snprintf(payload, sizeof(payload), "{\"temperature\": \"%f\", \"humidity\": \"%f\"}", round(temperature * 10.0) / 10.0, humidity);
    client.publish(topic, payload);
    Serial.print("Sent data: ");
    Serial.println(payload);
  }
}
