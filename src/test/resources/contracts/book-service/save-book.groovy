import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should save book"

    bookId = "test-contract-id"

    request {
        url "/api/v1/books"
        method POST()

        headers {
            contentType applicationJson()
        }

        body """
            {
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

    response {
        status OK()

        headers {
            contentType applicationJson()

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
}