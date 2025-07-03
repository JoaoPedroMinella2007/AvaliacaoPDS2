package model;

import dal.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {
    
    public void inserirCliente(Produto produto) throws SQLException{
        String sql = "INSERT INTO produtos (id, descricao, valor, quantidade_estoque) VALUES (?,?,?,?)";
        
        try (Connection conn =  ConexaoBD.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getValor());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            
            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");
            
        }catch(SQLException e){
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }
    
    public void listarProdutos() throws SQLException{
        String sql = "SELECT * FROM produtos";
        
        try(Connection conn = ConexaoBD.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                ResultSet rs = stmt.executeQuery();
                
            while(rs.next()){
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                long valor = rs.getLong("valor");
                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                
                System.out.println("ID: " + id + "\nDescrição: " + descricao + "\nValor: " + valor +
                        "\nQuantidade em estoque: " + quantidadeEstoque);
            }           
        }catch(SQLException e){
            System.out.println("Erro ao listar produtos: " + e.getMessage());
    }
    }
}
    

