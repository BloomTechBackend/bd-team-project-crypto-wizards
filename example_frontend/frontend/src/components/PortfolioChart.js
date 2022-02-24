import React, {useEffect, useRef, useState} from "react";
import {ArcElement, Chart as ChartJS, Legend, Tooltip} from 'chart.js';
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
                            '#0CA6F2',
                            '#26E51C',
                            '#E51C74',
                            '#F2640C',
                            '#F2D70C',
                            '#0CF270',
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
                            // display: 'auto',
                            display: true,
                            color: '#000',
                            align: 'end',
                            anchor: 'end',
                            clamp: true,
                            formatter: function(value, context) {
                                let total = 0;
                                let assetValues = assets.map(asset => asset.current_price * assetQuantityMap[asset.id]);
                                for (let i = 0; i < assetValues.length; i++) {
                                    total += assetValues[i];
                                }
                                total = total.toFixed(0);
                                let percent = ((value / total) * 100).toFixed(0);

                                if (percent != 0) {
                                    let symbol = context.chart.data.datasets[0].label[0][context.dataIndex];
                                    let totalValue = context.chart.data.datasets[0].data[context.dataIndex];
                                    return '   ' + symbol.toUpperCase() + '\n$' + totalValue.toFixed(2);
                                } else {
                                    return '';
                                }
                            },
                        },
                        // TOTAL VALUE
                        title: {
                            display: true,
                            text: function() {
                                let sum = 0;
                                let assetValues = assets.map(asset => asset.current_price * assetQuantityMap[asset.id]);
                                for (let i = 0; i < assetValues.length; i++) {
                                    sum += assetValues[i];
                                }
                                sum = sum.toFixed(2);
                                return 'Total $' + sum;
                            },
                            color: '#000',
                            font: {
                                size: 20,
                            },
                            align: 'start',
                        },
                        legend: {
                            display: true,
                            position: 'left',
                            align: 'center',
                            labels: {
                                filter: function(legendItem, data) {
                                    let total = 0;
                                    let assetValues = assets.map(asset => asset.current_price * assetQuantityMap[asset.id]);
                                    for (let i = 0; i < assetValues.length; i++) {
                                        total += assetValues[i];
                                    }
                                    total = total.toFixed(0);

                                    let label = legendItem.text;
                                    let labelIndex = _.findIndex(data.labels, (labelName) => labelName === label);
                                    let usd = data.datasets[0].data[labelIndex];

                                    let percent = ((usd / total) * 100).toFixed(1);

                                    legendItem.text = [`${legendItem.text}`];
                                    legendItem.text.push(`$${usd.toFixed(2)}`);
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
                        // HOVER LABEL
                        tooltip: {
                            enabled: true,
                            callbacks: {
                                title: function(tooltipItems) {
                                    let title = tooltipItems[0].label;
                                    return title;
                                },
                                label: function(tooltipItems) {
                                    let total = 0;
                                    let assetValues = assets.map(asset => asset.current_price * assetQuantityMap[asset.id]);
                                    for (let i = 0; i < assetValues.length; i++) {
                                        total += assetValues[i];
                                    }
                                    total = total.toFixed(0);
                                    let value = tooltipItems.chart.data.datasets[0].label[1][tooltipItems.dataIndex];
                                    let percent = ((value / total) * 100).toFixed(1);

                                    let multiLine = ['$' + value.toFixed(2)];
                                    multiLine.push(percent + '%');

                                    return multiLine;
                                }
                            }
                        },
                    },
                    layout: {
                        padding: {
                            left: 0,
                            right: 0,
                            bottom: 20,
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
                <canvas id="myChart" ref={chartRef}  width="400" height="400"/>
            </div>
        );
    };

    export default PortfolioChart;
