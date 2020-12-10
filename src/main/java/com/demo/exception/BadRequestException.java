package com.demo.exception;

import com.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BadRequestException extends Exception {
    private static final long serialVersionUID = -5040102560780177984L;

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        Map<Long,String> map = list.stream().collect(Collectors.toMap())
    }
}
