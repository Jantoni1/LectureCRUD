


//
//    function buildHtmlTable(response, selector) {
//        var columns = addAllColumnHeaders(response, selector);
//        console.log("kek");
//        for (var i = 0; i < response.length; i++) {
//            console.log("WTF");
//            var row$ = $('<tr/>');
//            for (var colIndex = 0; colIndex < columns.length; colIndex++) {
//                var cellValue = response[i][columns[colIndex]];
//                if (cellValue == null) cellValue = "";
//                row$.append($('<td/>').html(cellValue));
//            }
//            $(selector).append(row$);
//        }
//    }

// Adds a header row to the table and returns the set of columns.
// Need to do union of keys from all records as some records may not contain
// all records.
function addAllColumnHeaders(myList, selector) {
    var columnSet = [];
    var headerTr$ = $('<tr/>');

    for (var i = 0; i < myList.length; i++) {
        var rowHash = myList[i];
        for (var key in rowHash) {
            if ($.inArray(key, columnSet) == -1) {
                columnSet.push(key);
                headerTr$.append($('<th/>').html(key));
            }
        }
    }
    $(selector).append(headerTr$);

    return columnSet;
}

var xhttp = new XMLHttpRequest();
xhttp.open("GET", "http://localhost:8080/lectures", false);
xhttp.setRequestHeader("Content-type", "application/json");
xhttp.send();
var myBooks = JSON.parse(xhttp.responseText); //JSON.parse(xhttp.responseText);


// EXTRACT VALUE FOR HTML HEADER.
// ('Book ID', 'Book Name', 'Category' and 'Price')
var col = [];
for (var i = 0; i < myBooks.length; i++) {
    for (var key in myBooks[i]) {
        if (col.indexOf(key) === -1) {
            col.push(key);
        }
    }
}

// CREATE DYNAMIC TABLE.
var table = document.createElement("table");


// CREATE HTML TABLE HEADER ROW USING THE EXTRACTED HEADERS ABOVE.

var tr = table.insertRow(-1);                   // TABLE ROW.

for (var i = 0; i < col.length; i++) {
    var th = document.createElement("th");      // TABLE HEADER.
    th.innerHTML = col[i];
    tr.appendChild(th);
}

// ADD JSON DATA TO THE TABLE AS ROWS.
for (var i = 0; i < myBooks.length; i++) {

    tr = table.insertRow(-1);

    for (var j = 0; j < col.length; j++) {
        var tabCell = tr.insertCell(-1);
        tabCell.innerHTML = myBooks[i][col[j]];
    }
}

// FINALLY ADD THE NEWLY CREATED TABLE WITH JSON DATA TO A CONTAINER.
var divContainer = document.getElementById("showData");
divContainer.innerHTML = "";
divContainer.appendChild(table);



