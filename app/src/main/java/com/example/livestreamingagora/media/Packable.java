package com.example.livestreamingagora.media;

public interface Packable {
    ByteBuf marshal(ByteBuf out);
}