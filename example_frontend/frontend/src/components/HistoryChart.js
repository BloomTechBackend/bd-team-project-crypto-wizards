import React, {useEffect, useRef, useState} from "react";
import {Chart as ChartJs} from 'chart.js';

const HistoryChart = ({data}) => {
    const masterList = [
        "bitcoin", "ethereum", "tether",
        "binancecoin", "usd-coin", "ripple",
        "cardano", "solana", "avalanche-2",
        "terra-luna", "polkadot", "dogecoin",
        "binance-usd", "shiba-inu", "matic-network",
        "crypto-com-chain", "terrausd", "wrapped-bitcoin",
        "dai", "cosmos", "litecoin",
        "chainlink", "near", "tron",
        "ftx-token", "algorand", "bitcoin-cash",
        "staked-ether", "okb", "stellar",
        "leo-token", "fantom", "uniswap",
        "decentraland", "hedera-hashgraph", "internet-computer",
        "the-sandbox", "axie-infinity", "ethereum-classic",
        "elrond-erd-2", "vechain", "theta-token",
        "filecoin", "ecomi", "tezos",
        "klay-token", "monero", "compound-ether",
        "cdai", "the-graph"
    ];
    const chartRef = useRef();
    const {details} = data;
    const [assets, setAssets] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        // Make sure canvas has been rendered
        if (chartRef && chartRef.current && details) {
            console.log("canvas");
            const chartInstance = new ChartJs(chartRef.current, {
                type: 'pie',
                data: {
                    labels: [1,2,3,4,5,6,7],
                    datasets: [
                        {
                            label: `${details.name}`,
                            data: `${details.symbol}`,
                            // data: `${details.current_price} price`,
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
                },
                options: {
                    // scales: {
                    // },
                    plugins: {
                        // tooltip: {
                        //     enabled: false
                        // },
                        dataLabels: {
                            formatter: (value, context) => {

                                console.log(value);
                                console.log(context.chart.data.datasets[0].data);
                                const dataPoints = context.chart.data.datasets[0].data;
                                function totalSum(total, datapoint) {
                                    return total + datapoint;
                                }
                                const totalValue = dataPoints.reduce(totalSum, 0);
                                const percentageValue = (value / totalValue * 100).toFixed(1);
                                const display = [`$${value}`, `${percentageValue}%`, 'Company'];

                                return display;
                            }
                        }
                    }
                }
               // plugins: [ChartDataLabels]
            });
        }
    });

    //     const fetchData = async () => {
    //         setIsLoading(true);
    //         const response = await coinGecko.get("/coins/markets/", {
    //             // Params used in coinGecko API
    //             params: {
    //                 vs_currency: "usd",
    //                 // pass in a list
    //                 // pass string to array
    //                 ids: masterList.join(","),
    //             },
    //         });
    //
    //         setAssets(response.data);
    //         setIsLoading(false);
    //         // console.log(response.data);
    //         // console.log(assets);
    //     };
    //     fetchData();
    // }, []);

    console.log("Chart");

    return (
        // White background
        <div className="bg-white boarder mt-2 rounded p-3" >
            <div></div>
        <div>
            <canvas ref={chartRef} id="myChart" outerRadius={250} />
        </div>
        </div>
    );
};

export default HistoryChart;
