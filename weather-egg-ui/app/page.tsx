import "./globals.css";
import BusCard from "./components/BusCard";
import WeatherCard from "./components/WeatherCard";
import { Suspense } from "react";
import Sidebar from "./components/Sidebar";

export default function Page() {
  return (
    <div>
      <div className="flex">
        <Sidebar />
        <Suspense>
          <BusCard busStopCode={46631} serviceNumber={161} />
        </Suspense>
        <Suspense>
          <BusCard busStopCode={46631} serviceNumber={161} />
        </Suspense>
        <Suspense>
          <WeatherCard />
        </Suspense>
      </div>
    </div>
  );
}
