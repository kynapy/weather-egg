import { getCurrentWeather } from "../services/WeatherServices";
import { WeatherData } from "../types/WeatherData";

const WeatherCard = async () => {
  const weatherData: WeatherData = await getCurrentWeather();

  return (
    <div
      className="p-4 pl-10 rounded-xl shadow-sm text-white w-full"
      style={{ backgroundColor: "#61616180" }}
    >
      <p className="text-4xl font-bold">{weatherData.temperature}°C</p>
      <p>Humidity: {weatherData.humidity}%</p>
      {/* TODO: Change date time formatting */}
      <p>Last updated: 8h ago</p>
    </div>
  );
};

export default WeatherCard;
