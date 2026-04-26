import { getBusTiming } from "../services/LtaBusApiServices";

interface BusCardProps {
  busStopCode: number;
  serviceNumber: number;
}

const BusCard = async ({ busStopCode, serviceNumber }: BusCardProps) => {
  const busTiming = await getBusTiming(busStopCode, serviceNumber);

  return busTiming ? (
    <div
      style={{ backgroundColor: "#616161", whiteSpace: "pre-line" }}
      className="h-40 w-80 rounded-xl p-6 text-white shadow-sm"
    >
      {busTiming["Services"] && busTiming["Services"]?.length != 0 ? (
        <div>
          Bus Stop: {"\n"}
          Bus Number: {serviceNumber + "\n"}
          Arrival time:{" "}
          {busTiming["Services"][0]["NextBus"]["EstimatedArrival"] + "\n"}
          Arrival time 2:{" "}
          {busTiming["Services"][0]["NextBus2"]["EstimatedArrival"] + "\n"}
          Arrival time 3:{" "}
          {busTiming["Services"][0]["NextBus3"]["EstimatedArrival"] + "\n"}
        </div>
      ) : (
        <div>No {serviceNumber} buses running currently</div>
      )}
    </div>
  ) : (
    <></>
  );
};

export default BusCard;
