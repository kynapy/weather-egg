interface BusTiming {
  OriginCode: string;
  DestinationCode: string;
  EstimatedArrival: string;
  // "EstimatedArrival": "2025-10-14T20:35:31+08:00",
  Monitored: number;
  Latitude: string;
  Longitude: string;
  VisitNumber: string;
  Load: string;
  Feature: string;
  Type: string;
  ServiceNo: string;
}

interface BusTimingResponse {
  BusStopCode: string;
  Services: Array<BusTiming>;
}

export type { BusTiming, BusTimingResponse };
