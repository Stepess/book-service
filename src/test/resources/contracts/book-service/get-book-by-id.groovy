import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return book by id"

    bookId = "test-contract-id"

    request {
        url "/api/v1/books/${bookId}"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body """
            {
                "id": "${bookId}",
                "title": "Test Book",
                "description": "Awesome book about testing",
                "price": 42.13,
                "quantity": 11,
                "authors": [
                    {
                        "firstName": "Stepan",
                        "lastName": "Yershov"
                    }
                ],
                "genres": [
                    "kek",
                    "lol"
                ],
                "tags": [
                    "bestseller"
                ]
            }
        """
    }
}