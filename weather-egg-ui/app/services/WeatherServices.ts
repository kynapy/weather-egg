import axios from "axios";
import { WeatherData } from "../types/WeatherData";

const getCurrentWeather = async (): Promise<WeatherData> => {
  const { data } = await axios.get("http://localhost:8080/current-weather");
  return data;
};

export { getCurrentWeather };
