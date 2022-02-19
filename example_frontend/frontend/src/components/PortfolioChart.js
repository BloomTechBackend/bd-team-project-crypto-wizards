import React, {useEffect, useRef, useState} from "react";
import {Chart as ChartJS, ArcElement, Tooltip, Legend} from 'chart.js';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

const PortfolioChart = () => {
    const assetQuantityMap = [
        "bitcoin", "ethereum", "tether",
        "binancecoin", "usd-coin", "ripple",
    ];

    let data = {
        labels: [...assetQuantityMap],
        datasets: [
            {
                label: `Assets in Portfolio`,
                data: [20, 20, 20, 20, 20],
                boarderWidth: 5,
                backgroundColor: [
                    'rgba(255, 26, 104, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(182, 51, 204, 1)'
                ],
                borderColor: 'rgba(0, 0, 0, 1)',
            },
        ],
    };

    let options = {
        maintainAspectRatio: false,
        scales: {
        },
        legend: {
            labels: {
                fontSize: 24,
            },
        },
    }
    // let options = {
    //
    //     dataLabels: {
    //         formatter: (value, context) => {
    //
    //             console.log(value);
    //             console.log(context.chart.data.datasets[0].data);
    //             const dataPoints = context.chart.data.datasets[0].data;
    //             function totalSum(total, datapoint) {
    //                 return total + datapoint;
    //             }
    //             const totalValue = dataPoints.reduce(totalSum, 0);
    //             const percentageValue = (value / totalValue * 100).toFixed(1);
    //             const display = [`$${value}`, `${percentageValue}%`, 'Company'];
    //
    //             return display;
    //         }
    //     }
    // }
    console.log("Chart");

    return (
        <div className="bg-white boarder mt-2 rounded p-3" >
            {/*<canvas id="myChart" width="400" height="400">*/}
                <Pie
                    // Library needs key specified to track changes in dataset series
                    datasetIdKey='id'
                    data={data}
                    height={300}
                    options={options}
                />
            {/*</canvas>*/}
        </div>
    );
};

export default PortfolioChart;
