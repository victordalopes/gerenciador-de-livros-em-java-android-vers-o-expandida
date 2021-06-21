package com.example.gerenciadordelivros.data;

public class UsuarioContract {

    public static final String TABLE_NAME = "usuario";

    public static final class Columns{
        public static final String _ID = "_id";
        public static final String login = "login";
        public static final String senha = "senha";
        public static final String status = "status";
        public static final String tipo = "tipo";
    }
}
