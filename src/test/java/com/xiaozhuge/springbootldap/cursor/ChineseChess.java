package com.xiaozhuge.springbootldap.cursor;

public class ChineseChess {
    public enum Piece {
        SOLDIER, CANNON, CHARIOT, HORSE, ELEPHANT, ADVISOR, GENERAL
    }

    public enum Player {
        RED, BLACK
    }

    public static class ChessPiece {
        private Piece piece;
        private Player player;

        public ChessPiece(Piece piece, Player player) {
            this.piece = piece;
            this.player = player;
        }

        public Piece getPiece() {
            return piece;
        }

        public Player getPlayer() {
            return player;
        }
    }

    private ChessPiece[][] board;

    public ChineseChess() {
        board = new ChessPiece[10][9];
        // 初始化棋盘，放置棋子
    }

    public ChessPiece getPieceAt(int row, int col) {
        return board[row][col];
    }

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // 检查移动是否合法，然后移动棋子
    }
}