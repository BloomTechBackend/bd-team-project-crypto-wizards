import React, {useEffect, useRef, useState} from "react";
import {Chart as ChartJS, ArcElement, Tooltip, Legend} from 'chart.js';
// Register all controllers, elements, scales, plugins from Chart.js
import Chart from 'chart.js/auto';


ChartJS.register(ArcElement, Tooltip, Legend);

const PortfolioChart = ({assets, assetQuantityMap}) => {

    const chartRef = useRef();
    const [myChart, setMyChart] = useState(null);
    const _ = require('lodash');

    useEffect(() => {
        if (chartRef && chartRef.current) {
            const chartInstance = new Chart(chartRef.current, {
                type: 'doughnut',
                data: {
                    labels: assets.map(asset => [asset.name, asset.symbol].join(' \r')),
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
                            fontsize: 24,
                        },
                        legend: {
                            display: true,
                            position: 'left',
                            align: 'center',
                            labels: {
                                filter: (legendItem, data) => {
                                    // Retrieve the data corresponding to that label
                                    const label = legendItem.text
                                    const labelIndex = _.findIndex(data.labels, (labelName) => labelName === label) // lodash used here
                                    const qtd = data.datasets[0].data[labelIndex]

                                    // Mutate the legendItem to include the new text
                                    legendItem.text = `${legendItem.text} : ${qtd}`

                                    // Filter method expects a bool, so return true to show the modified legendItem in the legend
                                    return true
                                },
                                fontSize: 36,
                                color: '#000',
                                padding: 20,
                            },
                        },
                    },
                    layout: {
                        padding: {
                            left: 50,
                            right: 0,
                            bottom: 0,
                            top: 0,
                        },
                    },
                    tooltips: {
                        enabled: true,
                        callbacks: {
                            label: (tooltipItem, data) => {
                                const label = data.labels[tooltipItem.index];
                                const val =
                                    data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index];
                                return label + ": $" + val;
                            }
                        }
                    },
                },
            });
            return () => {
                chartInstance.destroy();
            }
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
