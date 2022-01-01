package com.ifly.repositories;

import com.ifly.domain.EmpresaAerea;
import com.ifly.domain.Rota;
import com.ifly.domain.Usuario;
import com.ifly.domain.Voo;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class VooRepository extends BaseRepository{
    AirlineRepository airlineRepository = new AirlineRepository();
    RotaRepository rotaRepository = new RotaRepository();

    public ArrayList<Voo> getVoosByOriginAndDestinyAndDate(int origin, int destiny, Timestamp date){
        ArrayList<Voo> voos = new ArrayList<>();
        String sql =
                "SELECT * from voo v" +
                        "   INNER JOIN (" +
                        "           SELECT r.id, a1.id AS origem, a2.id AS destino, r.distancia FROM rota r" +
                        "               INNER JOIN aeroporto a1" +
                        "                   ON a1.id = r.id_aeroporto_partida" +
                        "               INNER JOIN aeroporto a2" +
                        "                   ON a2.id = r.id_aeroporto_chegada" +
                        "       ) rotas" +
                        "       ON rotas.id = v.id_rota" +
                        "   WHERE origem = ? AND destino=? AND horario>=? AND horario<=?";

        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, origin);
                stm.setInt(2, destiny);
                stm.setTimestamp(3, date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);// w ww.  j ava  2  s  .co m
                cal.add(Calendar.DATE, 1); //minus number would decrement the days
                Timestamp dayAfterDate = new Timestamp(cal.getTime().getTime());
                stm.setTimestamp(4, dayAfterDate);

                ResultSet set = stm.executeQuery();
                while(set.next()){
                    Voo voo = new Voo();
                    voo.setId(set.getLong("id"));
                    EmpresaAerea empresaAerea = airlineRepository.getEmpresaById(set.getLong("id_empresa_aerea"));
                    voo.setEmpresaAerea(empresaAerea);
                    Rota rota = rotaRepository.getRotaById(set.getLong("id_rota"));
                    voo.setRota(rota);
                    voo.setCapacidade(set.getInt("capacidade"));
                    voo.setValor(set.getFloat("valor"));
                    voo.setHorario(set.getTimestamp("horario"));
                    voos.add(voo);
                }
            }else{
                openConnection();
            }
        }catch (SQLException e){

        }
        return voos;
    }

    public ArrayList<Voo> getVoosByOriginAndDestinyAirlineAndDate(int origin, int destiny,int airline, Timestamp date){
        ArrayList<Voo> voos = new ArrayList<>();
        String sql =
                "SELECT * from voo v" +
                        "   INNER JOIN (" +
                        "           SELECT r.id, a1.id AS origem, a2.id AS destino, r.distancia FROM rota r" +
                        "               INNER JOIN aeroporto a1" +
                        "                   ON a1.id = r.id_aeroporto_partida" +
                        "               INNER JOIN aeroporto a2" +
                        "                   ON a2.id = r.id_aeroporto_chegada" +
                        "       ) rotas" +
                        "       ON rotas.id = v.id_rota" +
                        "   WHERE origem = ? AND destino=? AND horario>=? AND horario<=? AND id_empresa_aerea = ?";

        try {
            if(connection.isValid(3)){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, origin);
                stm.setInt(2, destiny);
                stm.setTimestamp(3, date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);// w ww.  j ava  2  s  .co m
                cal.add(Calendar.DATE, 1); //minus number would decrement the days
                Timestamp dayAfterDate = new Timestamp(cal.getTime().getTime());
                stm.setTimestamp(4, dayAfterDate);
                stm.setInt(5, airline);

                ResultSet set = stm.executeQuery();
                while(set.next()){
                    Voo voo = new Voo();
                    voo.setId(set.getLong("id"));
                    EmpresaAerea empresaAerea = airlineRepository.getEmpresaById(set.getLong("id_empresa_aerea"));
                    voo.setEmpresaAerea(empresaAerea);
                    Rota rota = rotaRepository.getRotaById(set.getLong("id_rota"));
                    voo.setRota(rota);
                    voo.setCapacidade(set.getInt("capacidade"));
                    voo.setValor(set.getFloat("valor"));
                    voo.setHorario(set.getTimestamp("horario"));
                    voos.add(voo);
                }
            }else{
                openConnection();
            }
        }catch (SQLException e){

        }
        return voos;
    }
}
