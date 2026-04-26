import BusCard from "../components/BusCard";
import WeatherCard from "../components/WeatherCard";
import { Suspense } from "react";
import Sidebar from "../components/Sidebar";

export default function Page() {
  return (
    <div className="min-h-screen flex">
      <div className="w-64 shrink-0">
        <Sidebar />
      </div>

      <div className="flex-1 p-4">
        <h2 className="text-3xl font-bold mb-4">Overview</h2>
        <div className="flex items-start gap-4">
          <Suspense>
            <BusCard busStopCode={46631} serviceNumber={161} />
          </Suspense>
          <Suspense>
            <BusCard busStopCode={46671} serviceNumber={912} />
          </Suspense>
          <Suspense>
            <WeatherCard />
          </Suspense>
        </div>
      </div>
    </div>
  );
}
