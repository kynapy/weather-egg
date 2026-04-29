import axios from "axios";
import { WeatherData } from "../types/WeatherData";

const weatherApiUrl = process.env.NEXT_BACKEND_API_URL;

const getCurrentWeather = async (): Promise<WeatherData> => {
  const { data } = await axios.get(`${weatherApiUrl}/current-weather`);
  return data;
};

export { getCurrentWeather };
