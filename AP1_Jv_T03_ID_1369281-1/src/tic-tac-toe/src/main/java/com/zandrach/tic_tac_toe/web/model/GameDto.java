package com.zandrach.tic_tac_toe.web.model;

import java.util.UUID;

public record GameDto(UUID id, BoardDto board) {
}
