import type { Metadata } from 'next';
import "./globals.css";

export const metadata: Metadata = {
  title: "Weather Egg Dashboard",
  description: "Dashboard for Weather Egg, a weather and auto-watering system built with Next.js",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body>
        <div id="root">{children}</div>
      </body>
    </html>
  )
}
