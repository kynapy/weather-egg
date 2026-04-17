import "./globals.css";
import BusCard from "./components/BusCard";
import { Suspense } from "react";

export default function Page() {
  return (
    <div>
      <div>
        <Suspense>
          <BusCard busStopCode={46631} serviceNumber={161} />
        </Suspense>
        <Suspense>
          <BusCard busStopCode={46631} serviceNumber={161} />
        </Suspense>
      </div>
    </div>
  );
}
