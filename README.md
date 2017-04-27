# BusinessCardParser

To build and run test:
    mvn test

To filter out certain lines containing certain words for name, phone, or email lines edit:
    src/resources/BusinessCardParser.config

Example:
{
    nameFilters :[
        "Inc."
    ],
    phoneFilters: [
        "Fax",
        "(f)"
    ],
    emailFilters :[
    ]
}