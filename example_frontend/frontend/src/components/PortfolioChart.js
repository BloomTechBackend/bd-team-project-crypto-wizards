import React, {useEffect, useRef, useState} from "react";
import {Chart as ChartJS, ArcElement, Tooltip, Legend} from 'chart.js';
import Chart from 'chart.js/auto';
import ChartDataLabels from 'chartjs-plugin-datalabels';


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
                    //labels: assets.map(asset => [asset.name, asset.symbol, asset.id, asset.current_price * assetQuantityMap[asset.id]].join(' ')),
                    labels: assets.map(asset => asset.name),
                    datasets: [{
                        label: [assets.map(asset => asset.symbol),
                            assets.map(asset => asset.current_price * assetQuantityMap[asset.id]),
                            assets.map(asset => asset.price_change_24),
                            assets.map(asset => asset.market_cap),
                            assets.map(asset => asset.image)],
                        data: assets.map(asset => asset.current_price * assetQuantityMap[asset.id]),
                        backgroundColor: [
                            '#ac32e4',
                            '#7918f2',
                            '#4801ff',
                            '#2314f9',
                            'rgba(255, 26, 104, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)',
                            'rgba(182, 51, 204, 1)',
                        ],
                        // borderColor: 'rgba(0, 0, 0, 1)',
                        boarderWidth: 1,
                        hoverBorderWidth: 1,
                        hoverBorderColor: '#000',
                    }],
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        // CHART LABEL
                        datalabels: {
                            display: true,
                            color: 'black',
                            anchor: 'center',
                            // data from labels
                            formatter: function(value, context) {
                                const symbol = context.chart.data.datasets[0].label[0][context.dataIndex];
                                return symbol.toUpperCase() + '\n$' +
                                context.chart.data.datasets[0].data[context.dataIndex];
                            },
                        },
                        // TOTAL VALUE
                        // TODO Calculate total value
                        title: {
                            display: true,
                            // text: assets.map(asset => asset.current_price * assetQuantityMap[asset.id]),
                            text: 'Total Portfolio Value',
                            fontsize: 24,
                            align: 'start',
                        },
                        // HOVER LABEL
                        // TODO Fix title data
                        tooltip: {
                            enabled: true,
                            callbacks: {
                                label: function(context) {
                                    let label = new Intl.NumberFormat('en-US', {
                                        style: 'percent',
                                        minimumFractionDigits: 0,
                                        maximumFractionDigits: 0
                                    }).format(context.formattedValue);
                                    return label;
                                },
                                title: function(context) {
                                    let title = context[0].label;
                                    return title;
                                }
                            }
                            //     {
                            //     label: (tooltipItem, data) => {
                            //         const label = data.labels[tooltipItem.index];
                            //         const val =
                            //             data.datasets[tooltipItem.datasetIndex].data[tooltipItem.index];
                            //         // return label.split('\n ')[1] + ": $" + val;
                            //         return label + ": $" + val;
                            //     }
                            // }
                        },
                        legend: {
                            display: true,
                            position: 'left',
                            align: 'center',
                            labels: {
                                filter: function(legendItem, data) {
                                    const label = legendItem.text;
                                    const labelIndex = _.findIndex(data.labels, (labelName) => labelName === label);
                                    const usd = data.datasets[0].data[labelIndex];
                                    const percent = Math.round(usd *.1);

                                    legendItem.text = [`${legendItem.text}`];
                                    legendItem.text.push(`$${usd}`);
                                    legendItem.text.push(`${percent}%`);

                                    return true;
                                },
                                fontSize: 36,
                                color: '#000',
                                font: {
                                    weight: 'bold'
                                },
                                padding: 40,
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
                },
                plugins: [ChartDataLabels]
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
