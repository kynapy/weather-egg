"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

const links = [
  { name: "Home", href: "/home" },
  { name: "Weather Monitoring", href: "/weather" },
  { name: "Plant Monitoring", href: "/plants" },
  { name: "Bus Tracking", href: "/bus" },
];

const Sidebar = () => {
  const pathname = usePathname();

  return (
    <div className="h-screen p-5">
      <h2 className="text-2xl font-bold mb-4">Weather Egg</h2>
      <ul className="flex flex-col">
        {links.map((link) => (
          <Link
            key={link.name}
            href={link.href}
            className={"mb-2 cursor-pointer bg-gray-200 rounded-lg px-3 py-2"}
            // TODO: Change color of text and make active link more visible
          >
            {link.name}
          </Link>
        ))}
      </ul>
    </div>
  );
};

export default Sidebar;
