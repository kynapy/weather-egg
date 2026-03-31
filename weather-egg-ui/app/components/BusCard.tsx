import { Box } from "@mui/material";
import { useEffect, useState } from "react";
import { getBusTiming } from "../services/LtaBusApiServices";
import type { BusTimingResponse } from "../types/BusTiming";

interface BusCardProps {
  busStopCode: number;
  serviceNumber: number;
}

const BusCard = ({ busStopCode, serviceNumber }: BusCardProps) => {
  const [busTiming, setBusTiming] = useState<BusTimingResponse>();

  useEffect(() => {
    getBusTiming(busStopCode, serviceNumber).then((result) => {
      setBusTiming(result);
    });
  }, [busStopCode, serviceNumber]);

  useEffect(() => {
    if (busTiming) {
      console.log(busTiming["Services"][0]);
    }
  }, [busTiming]);

  return (
    <Box>
      {busTiming ? (
        <Box
          sx={{
            backgroundColor: "white",
            padding: "20px 20px",
            border: "1px solid black",
            margin: "20px 0px",
            color: "black",
            borderRadius: "5px",
            whiteSpace: "pre-line",
          }}
        >
          Bus Stop: {"\n"}
          Bus Number: {busTiming["Services"][0]["ServiceNo"] + "\n"}
          Arrival time:{" "}
          {busTiming["Services"][0]["NextBus"]["EstimatedArrival"] + "\n"}
          Arrival time 2:{" "}
          {busTiming["Services"][0]["NextBus2"]["EstimatedArrival"] + "\n"}
          Arrival time 3:{" "}
          {busTiming["Services"][0]["NextBus3"]["EstimatedArrival"] + "\n"}
        </Box>
      ) : (
        <Box />
      )}
    </Box>
  );
};

export default BusCard;
