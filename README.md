# ⚠️ This README is a WIP 
---

# What is auto data?
Auto data is an application that allows you generate CSV's, excel files and SQL tables of any size filled with dummy data for testing purposes. Auto data works by providing a schema that has the following shape
```json
{
    "metadata": {
        "string": {
            "min": 50,
            "max": 100
        },
        "int": {...}
    }
	"columns": [
		{
            "name": "id",
            "datatype": "identifier"
        },
        {
            "name": "first_name",
            "datatype": "string(50)"
        },
        {
            "name": "last_name",
            "datatype": "string(100)"
        }
    ]
}
```
Out of the box auto data supports common data types and makes some assumptions about them.
For example, `identifier` is automatically treated as unique and non-nullable. All datatypes are non-nullable by default unless specified. If you just enter the value `string` then it will  equate to `string(max)` where max is the maximum number of allowed characters.
## Supported datatypes
- `identifier`, an auto-incrementing, unique and non-nullable integer
- `string(min, max)` a string with a minimum length of `min` and a maximum length of `max`
- `int(min, max)` an integer with a minimum value of `min` and a maximum value of `max`
- `decimal(min, max, precision, scale)` a double with a minimum value of `min` and a maximum value of `max`. The value will have `precision` digits in total (to the left and right of the decimal point), and will have `scale` values after the decimal point
- `uuid` this will generate a random v4 UUID

## Metadata
The schema also allows for metadata to be provided as seen above. This allows you to define global constraints on all datatypes. Note that local constraints take precedence over global constraints.

## Exporting
Auto data allows you to export the generated data as either a CSV or Excel spreadsheet. Soon we will support generating SQL statements, as well as connecting to a sql database and populating it straight from the app!

## Architecture
Auto data's front end communicates to out backend via a GraphQL api. The backend then generates the data and stores it in an S3 bucket. The data will live in the S3 bucket for only 30 minutes after it has been generated. Once the data has been downloaded on the front end. The client will send a mutation to the backend to  notify it that all has been completed.

The front end app will be written in typescript and svelte while the backend will be written in kotlin with spring boot.

In future we aim to provide a local version of this where you can just run the app locally and have the data downloaded straight to your file system.