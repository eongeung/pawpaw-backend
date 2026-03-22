package com.pawpaw.pawpaw.domain.hospital.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NaverSearchResponse {

    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
        private String title;
        private String address;
        private String roadAddress;
        private String telephone;
        private String mapx;
        private String mapy;
    }
}
