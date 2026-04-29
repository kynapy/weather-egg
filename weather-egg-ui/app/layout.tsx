import type { Metadata } from "next";
import "./globals.css";
import Sidebar from "./components/Sidebar";

export const metadata: Metadata = {
  title: "Weather Egg Dashboard",
  description:
    "Dashboard for Weather Egg, a weather and auto-watering system built with Next.js",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body className="antialiased">
        <div id="root" className="flex bg-white min-h-screen">
          <aside className="w-64 shrink-0">
            <Sidebar />
          </aside>
          <main className="flex-1">{children}</main>
        </div>
      </body>
    </html>
  );
}
