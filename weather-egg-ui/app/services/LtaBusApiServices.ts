import axios from "axios";
import type { BusTimingResponse } from "../types/BusTiming";

const API_KEY = process.env.NEXT_PUBLIC_DATAMALL_API_KEY;

const getBusTiming = async (
  busStopCode: number,
  serviceNo: number,
): Promise<BusTimingResponse> => {
  const { data } = await axios.get("http://localhost:8080/bus-arrival", {
    params: { busStopCode: busStopCode, serviceNumber: serviceNo },
    headers: { AccountKey: API_KEY },
  });
  return data;
};

export { getBusTiming };
