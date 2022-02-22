import React, {useEffect, useRef, useState} from "react";
import {Chart as ChartJS, ArcElement, Tooltip, Legend} from 'chart.js';
// Register all controllers, elements, scales, plugins from Chart.js
import Chart from 'chart.js/auto';


ChartJS.register(ArcElement, Tooltip, Legend);

const PortfolioChart = ({assets, assetQuantityMap}) => {
    const chartRef = useRef();
    const [myChart, setMyChart] = useState(null);


    useEffect(() => {

        if (chartRef && chartRef.current) {
            const myChart = new Chart(chartRef.current, {
                type: 'pie',
                data: {
                    labels: assets.map(asset => [asset.name, asset.id, asset.symbol].join(' \r')),
                    datasets: [{
                        label: `Assets in Portfolio`,
                        data: assets.map(asset => asset.current_price * assetQuantityMap[asset.id]),

                        backgroundColor: [
                            'rgba(255, 26, 104, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(182, 51, 204, 1)'
                        ],
                        // borderColor: 'rgba(0, 0, 0, 1)',
                        boarderWidth: 1,
                        hoverBorderWidth:1,
                        hoverBorderColor:'#000',
                    }],
                },
                options: {
                    maintainAspectRatio: false,
                    plugins: {
                        title: {
                            display: true,
                            text: 'Display Title Here',
                            fontsize: 30,
                        },
                        legend: {
                            display: true,
                            position: 'left',
                            labels: {
                                display: true,
                                fontSize: 24,
                            },
                        },

                    },
                    layout: {
                        padding: {
                            left: 100,
                            right: 0,
                            bottom: 0,
                            top: 0,
                        },
                    },
                    tooltips: {
                        enabled: true
                    },
                },
            });
        };
        setMyChart(myChart);
    }, [chartRef]);

    useEffect(() => {
        if (!myChart) return;
        myChart.update();
    }, [myChart]);

    console.log("Chart");

    return (
        <div className="bg-white boarder mt-2 rounded p-3">
            <canvas id="myChart" ref={chartRef} width="400" height="400"/>
        </div>
    );
};

export default PortfolioChart;
