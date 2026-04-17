import { Box } from "@mui/material";
import { getBusTiming } from "../services/LtaBusApiServices";

interface BusCardProps {
  busStopCode: number;
  serviceNumber: number;
}

const BusCard = async ({ busStopCode, serviceNumber }: BusCardProps) => {
  const busTiming = await getBusTiming(busStopCode, serviceNumber);

  return (
    <Box>
      {busTiming ? (
        <Box
          sx={{
            padding: "20px 20px",
            color: "black",
            borderRadius: "5px",
            whiteSpace: "pre-line",
          }}
        >
          {busTiming["Services"] ? (
            <div>
              Bus Stop: {"\n"}
              Bus Number: {busTiming["Services"][0]["ServiceNo"] + "\n"}
              Arrival time:{" "}
              {busTiming["Services"][0]["NextBus"]["EstimatedArrival"] + "\n"}
              Arrival time 2:{" "}
              {busTiming["Services"][0]["NextBus2"]["EstimatedArrival"] + "\n"}
              Arrival time 3:{" "}
              {busTiming["Services"][0]["NextBus3"]["EstimatedArrival"] + "\n"}
            </div>
          ) : (
            <div></div>
          )}
        </Box>
      ) : (
        <Box />
      )}
    </Box>
  );
};

export default BusCard;
