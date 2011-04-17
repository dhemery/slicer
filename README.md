# slicer-ng
slicer-ng is a TestNG DataProvider that slices and serves rows and columns from Excel spreadsheets.

## Usage

	slice(filename).iterator()

Returns an iterator that iterates over every row from sheet 0 of the spreadsheet in file `filename`.
For each row, the iterator yields an array of `Object`s, each of which is a `String`.
The array includes an element for each cell in the row.

	slice(filename).method(m).iterator()

Returns an iterator that iterates over every row from sheet 0 of the spreadsheet in file `filename`.
For each row, the iterator yields an array of `Object`s,
one `Object` for each of `m`'s parameters.
Each element of the array is of a type appropriate for the corresponding parameter of `m`.
If a row includes more values than are needed to supply `m`'s parameters,
the values from any excess values are ignored.

## Future features

### Select and order rows

	slice(filename).rows(3,1,2).iterator()

Yields row 3, then row 1, then row 2.

### Select and order columns

	slice(filename).columns(3,7,5).iterator()

Yields column 3, then 7, then 5 from each row.

### Select a sheet

	slice(filename).sheet(4).iterator()

Yields each cell from each row from sheet 4.

### Select columns by name

	slice(filename).columns("gumby", "pokey", "prickle", "goo").iterator()

Treats the first row as a header row, and excludes the header row in its output.
Yields the values from the columns named "gumby", "pokey", "prickle", and "goo".

### Select rows by name

	slice(filename).rows("March", "June", "September", "December").iterator()

Treats the first column as a header column, and excludes the header column from its output.
Yields the values from the rows named "March", "June", "September", and "December".

## Combinations

	slice(filename).sheet(3).rows(0,2,9).columns(1,2,4,7).method(myMethod).iterator()
