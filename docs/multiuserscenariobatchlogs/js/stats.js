var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "16650",
        "ok": "13255",
        "ko": "3395"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "3"
    },
    "maxResponseTime": {
        "total": "38009",
        "ok": "38009",
        "ko": "36443"
    },
    "meanResponseTime": {
        "total": "11848",
        "ok": "11417",
        "ko": "13531"
    },
    "standardDeviation": {
        "total": "11597",
        "ok": "11654",
        "ko": "11216"
    },
    "percentiles1": {
        "total": "7998",
        "ok": "6783",
        "ko": "11991"
    },
    "percentiles2": {
        "total": "21571",
        "ok": "20865",
        "ko": "25029"
    },
    "percentiles3": {
        "total": "32938",
        "ok": "33109",
        "ko": "31904"
    },
    "percentiles4": {
        "total": "34695",
        "ok": "34902",
        "ko": "33956"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2973,
    "percentage": 18
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 519,
    "percentage": 3
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9763,
    "percentage": 59
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3395,
    "percentage": 20
},
    "meanNumberOfRequestsPerSecond": {
        "total": "200.602",
        "ok": "159.699",
        "ko": "40.904"
    }
},
contents: {
"req_forward-log-req-33793": {
        type: "REQUEST",
        name: "forward-log-request",
path: "forward-log-request",
pathFormatted: "req_forward-log-req-33793",
stats: {
    "name": "forward-log-request",
    "numberOfRequests": {
        "total": "16650",
        "ok": "13255",
        "ko": "3395"
    },
    "minResponseTime": {
        "total": "1",
        "ok": "1",
        "ko": "3"
    },
    "maxResponseTime": {
        "total": "38009",
        "ok": "38009",
        "ko": "36443"
    },
    "meanResponseTime": {
        "total": "11848",
        "ok": "11417",
        "ko": "13531"
    },
    "standardDeviation": {
        "total": "11597",
        "ok": "11654",
        "ko": "11216"
    },
    "percentiles1": {
        "total": "7994",
        "ok": "6779",
        "ko": "11939"
    },
    "percentiles2": {
        "total": "21570",
        "ok": "20865",
        "ko": "25029"
    },
    "percentiles3": {
        "total": "32938",
        "ok": "33109",
        "ko": "31904"
    },
    "percentiles4": {
        "total": "34695",
        "ok": "34902",
        "ko": "33956"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 2973,
    "percentage": 18
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 519,
    "percentage": 3
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 9763,
    "percentage": 59
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 3395,
    "percentage": 20
},
    "meanNumberOfRequestsPerSecond": {
        "total": "200.602",
        "ok": "159.699",
        "ko": "40.904"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
