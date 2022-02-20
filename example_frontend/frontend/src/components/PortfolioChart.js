import React, {useEffect, useRef, useState} from "react";
import {Chart as ChartJS, ArcElement, Tooltip, Legend, Chart} from 'chart.js';
import { Pie } from 'react-chartjs-2';

ChartJS.register(ArcElement, Tooltip, Legend);

const PortfolioChart = () => {


    const portfolioAssets = [
        {id: 'bitcoin', symbol: 'btc', name: 'Bitcoin', current_price: 39.875},
        {id: 'ethereum', symbol: 'eth', name: 'Ethereum', current_price: 171.851},
        {id: 'tether', symbol: 'usdt', name: 'Tether', current_price: 271.002},
        {id: 'binancecoin', symbol: 'bnb', name: 'BNB', current_price: 308.77}
    ]

    let data = {
        labels: portfolioAssets.map(asset => [asset.name, asset.id, asset.symbol].join('\r')),

        datasets: [
            {
                label: `Assets in Portfolio`,
                data: portfolioAssets.map(asset => [asset.current_price]),
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
            <Pie
                data={data}
                height={300}
                options={options}
            />
        </div>
    );
};

export default PortfolioChart;
