package br.com.gorfo.mvnfxmlpadrao.dao;

import br.com.gorfo.mvnfxmlpadrao.ControllerTelaCadastroEmpresa;
import br.com.gorfo.mvnfxmlpadrao.beans.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
        public boolean inserir(Empresa empresa){
        String sql = "insert into tb_empresa (id_empresa,serial,cnpj,razao_social,fone,endereco,inscricao_estadual,tipo_sistema)values(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1,empresa.getId());
            stmt.setString(2,empresa.getSerial());
            stmt.setString(3,empresa.getCnpj());        
            stmt.setString(4,empresa.getRazao_social()); 
            stmt.setString(5,empresa.getFone()); 
            stmt.setString(6,empresa.getEndereco()); 
            stmt.setString(7,empresa.getInscricao_estadual()); 
            stmt.setString(8,empresa.getTipo_sistema());
            stmt.execute();
            System.out.println("Empresa inserida com sucesso!");
            return true;
        }catch (SQLException e){
            System.out.println("Erro ao inserir empresa: "+ e);
            return false;
        }
    }
    
    public List<Empresa> listar(){
        String sql = "select * from tb_empresa";
        List<Empresa> listaEmpresa = new ArrayList();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery(sql);
            while(resultado.next()){
                Empresa empresa = new Empresa(Integer.SIZE, sql, sql, sql, sql, sql, sql, sql);
                empresa.setId(resultado.getInt("id_empresa"));
                empresa.setSerial(resultado.getString("serial"));
                empresa.setCnpj(resultado.getString("cnpj"));
                empresa.setRazao_social(resultado.getString("razao_social"));
                empresa.setFone(resultado.getString("fone"));
                empresa.setEndereco(resultado.getString("endereco"));
                empresa.setInscricao_estadual(resultado.getString("inscricao_estadual"));
                empresa.setTipo_sistema(resultado.getString("tipo_sistema"));
                listaEmpresa.add(empresa);
            }
            System.out.println("Empresas listadas com sucesso!");
        }catch(SQLException e){
            System.out.println("Erro ao listar empresas: "+ e);
        }
        return listaEmpresa;
    }
    
    public boolean remover(Integer IdEmpresa){
        String sql = "delete from tb_empresa where id_empresa = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, IdEmpresa);
            stmt.execute();
            System.out.println("Empresa removida com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao remover empresa: "+ e);
            return false;
        }
    }
    
    public boolean atualizar(Empresa empresa){
        String sql = "update tb_empresa set id_empresa=?, serial=?, cnpj=?, razao_social=?, fone=?, endereco=?, inscricao_estadual=?, tipo_sistema=? where id_empresa=?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, empresa.getId());
            stmt.setString(2, empresa.getSerial());
            stmt.setString(3, empresa.getCnpj());
            stmt.setString(4, empresa.getRazao_social());
            stmt.setString(5, empresa.getFone());
            stmt.setString(6, empresa.getEndereco());
            stmt.setString(7, empresa.getInscricao_estadual());
            stmt.setString(8, empresa.getTipo_sistema());
            stmt.setInt(9,empresa.getId());
            stmt.execute();
            System.out.println("Empresa atualizada com sucesso!");
            return true;
        }catch(SQLException e){
            System.out.println("Erro ao atualizar empresa: "+ e);
            return false;
        }
    }
    
    public Empresa buscar(Integer IdEmpresa) throws SQLException{
        String sql = "select * from tb_empresa where id_empresa=?";
        Empresa retorno = new Empresa(Integer.SIZE, sql, sql, sql, sql, sql, sql, sql);
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, IdEmpresa);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next());
                retorno.setId(resultado.getInt("id_empresa"));
                retorno.setSerial(resultado.getString("serial"));
                retorno.setCnpj(resultado.getString("cnpj"));
                retorno.setRazao_social(resultado.getString("razao_social"));
                retorno.setFone(resultado.getString("fone"));
                retorno.setEndereco(resultado.getString("endereco"));
                retorno.setInscricao_estadual(resultado.getString("inscricao_estadual"));
                retorno.setTipo_sistema(resultado.getString("tipo_sistema"));
                System.out.println("Empresa localizada com sucesso!");
        return retorno;
    }
}
