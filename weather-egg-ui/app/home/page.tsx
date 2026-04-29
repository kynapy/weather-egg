import BusCard from "../components/BusCard";
import WeatherCard from "../components/WeatherCard";
import { Suspense } from "react";

// TODO: Create replacement for Suspense fallback
export default function Page() {
  return (
    <div className="p-4">
      <h2 className="text-3xl font-bold mb-4">Overview</h2>

      <div className="flex gap-4 items-stretch">
        <div className="flex-1 flex">
          <Suspense fallback={<div>Loading bus station info</div>}>
            <BusCard busStopCode={46631} serviceNumber={161} />
          </Suspense>
        </div>
        <div className="flex-1 flex">
          <Suspense fallback={<div>Loading bus station info</div>}>
            <BusCard busStopCode={46671} serviceNumber={912} />
          </Suspense>
        </div>
        <div className="flex-1 flex">
          <Suspense fallback={<div>Loading weather</div>}>
            <WeatherCard />
          </Suspense>
        </div>
      </div>
    </div>
  );
}
