package com.example.livestreamingagora.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
