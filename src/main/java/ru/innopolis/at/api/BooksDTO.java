package ru.innopolis.at.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BooksDTO {

    private String userId;
    private List<CollectionDTO> collectionOfIsbns;


    @Data
    @Builder
    static class CollectionDTO {

       private String isbn;

    }
}
