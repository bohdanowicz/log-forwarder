var stats = {
    type: "GROUP",
name: "All Requests",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "All Requests",
    "numberOfRequests": {
        "total": "16650",
        "ok": "10068",
        "ko": "6582"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "32"
    },
    "maxResponseTime": {
        "total": "70315",
        "ok": "70315",
        "ko": "70304"
    },
    "meanResponseTime": {
        "total": "40359",
        "ok": "34358",
        "ko": "49538"
    },
    "standardDeviation": {
        "total": "21449",
        "ok": "21947",
        "ko": "16961"
    },
    "percentiles1": {
        "total": "45294",
        "ok": "33863",
        "ko": "57016"
    },
    "percentiles2": {
        "total": "60350",
        "ok": "55422",
        "ko": "61457"
    },
    "percentiles3": {
        "total": "64818",
        "ok": "64349",
        "ko": "65539"
    },
    "percentiles4": {
        "total": "67722",
        "ok": "67378",
        "ko": "68508"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 1558,
    "percentage": 9
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 8509,
    "percentage": 51
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6582,
    "percentage": 40
},
    "meanNumberOfRequestsPerSecond": {
        "total": "136.475",
        "ok": "82.525",
        "ko": "53.951"
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
        "ok": "10068",
        "ko": "6582"
    },
    "minResponseTime": {
        "total": "2",
        "ok": "2",
        "ko": "32"
    },
    "maxResponseTime": {
        "total": "70315",
        "ok": "70315",
        "ko": "70304"
    },
    "meanResponseTime": {
        "total": "40359",
        "ok": "34358",
        "ko": "49538"
    },
    "standardDeviation": {
        "total": "21449",
        "ok": "21947",
        "ko": "16961"
    },
    "percentiles1": {
        "total": "45288",
        "ok": "33881",
        "ko": "57016"
    },
    "percentiles2": {
        "total": "60350",
        "ok": "55422",
        "ko": "61457"
    },
    "percentiles3": {
        "total": "64818",
        "ok": "64349",
        "ko": "65539"
    },
    "percentiles4": {
        "total": "67722",
        "ok": "67378",
        "ko": "68508"
    },
    "group1": {
    "name": "t < 800 ms",
    "htmlName": "t < 800 ms",
    "count": 1558,
    "percentage": 9
},
    "group2": {
    "name": "800 ms <= t < 1200 ms",
    "htmlName": "t >= 800 ms <br> t < 1200 ms",
    "count": 1,
    "percentage": 0
},
    "group3": {
    "name": "t >= 1200 ms",
    "htmlName": "t >= 1200 ms",
    "count": 8509,
    "percentage": 51
},
    "group4": {
    "name": "failed",
    "htmlName": "failed",
    "count": 6582,
    "percentage": 40
},
    "meanNumberOfRequestsPerSecond": {
        "total": "136.475",
        "ok": "82.525",
        "ko": "53.951"
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
