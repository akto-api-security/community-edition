import { useEffect } from "react";
import Highcharts from "highcharts/highmaps";

function ThreatWorldMap({ data, style, loading }) {
  useEffect(() => {
    const fetchMapData = async () => {
      const topology = await fetch(
        "https://code.highcharts.com/mapdata/custom/world.topo.json"
      ).then((response) => response.json());

      Highcharts.mapChart("threat-world-map-container", {
        chart: {
          map: topology,
          backgroundColor: "#fff",
        },

        title: {
          text: "Threat Actor Map",
        },

        credits: {
          enabled: false,
        },

        subtitle: {
          text: "",
        },

        legend: {
          enabled: false,
        },

        mapNavigation: {
          enabled: false,
        },

        mapView: {
          fitToGeometry: {
            type: "MultiPoint",
            coordinates: [
              [-164, 54], // Alaska west
              [-35, 84], // Greenland north
              [179, -38], // New Zealand east
              [-68, -55], // Chile south
            ],
          },
        },

        series: [
          {
            name: "Countries",
            color: "#E0E0E0",
            enableMouseTracking: false,
            states: {
              inactive: {
                enabled: true,
                opacity: 1,
              },
            },
          },
          {
            type: "mapbubble",
            name: "",
            data: data,
            minSize: "4%",
            maxSize: "4%",
            joinBy: ["iso-a2", "code"],
            marker: {
              fillOpacity: 0.5,
              lineWidth: 0,
            },
            tooltip: {
              pointFormat: "<b>{point.name}</b><br>Actors: {point.count}",
            },
          },
        ],
      });
    };

    fetchMapData();
  }, [data]);

  return <div id="threat-world-map-container" style={style}></div>;
}

export default ThreatWorldMap;