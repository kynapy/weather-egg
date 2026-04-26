import { getCurrentWeather } from "../services/WeatherServices";
import { WeatherData } from "../types/WeatherData";

const WeatherCard = async () => {
  const weatherData: WeatherData = await getCurrentWeather();

  return (
    <div
      className="p-6 rounded-xl h-40 shadow-sm text-white text-center mx-auto"
      style={{ backgroundColor: "#616161" }}
    >
      <p>Temperature: {weatherData.temperature}°C</p>
      <p>Humidity: {weatherData.humidity}%</p>
      {/* TODO: Change date time formatting */}
      <p>Last updated: {new Date(weatherData.timestamp).toTimeString()}</p>
    </div>
  );
};

export default WeatherCard;
