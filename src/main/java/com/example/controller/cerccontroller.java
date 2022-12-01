// MahasiswaController.java
package com.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.cerc;
import com.example.model.user;

@Controller
public class cerccontroller {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String index(Model model) {
        String sql = "SELECT * FROM CERCVIEW";
        List<cerc> cercList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(cerc.class));
        model.addAttribute("cerclist", cercList);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        String sql = "SELECT * FROM CERCVIEW";
        List<cerc> cercList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(cerc.class));
        model.addAttribute("cerclist", cercList);
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getUser() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute(name = "user") user user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
            user asli = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(user.class), username);
            model.addAttribute("asli", asli);
            String passasli = asli.getPassword();
            if (password.equals(passasli)) {
                return "redirect:/admin";
            }
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            model.addAttribute("invalidCredentials", true);
        }
        model.addAttribute("invalidCredentials", true);
        return "login";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String addanggota(cerc anggota, Model model ) {
        
                try {
                    String sql = "INSERT INTO ANGGOTA (nama_anggota, nim_anggota, id_minat, angkatan_anggota, id_anggota, deleted) VALUES (?, ?, ?, ?, ?, 'n')";
                    jdbcTemplate.update(sql,
                anggota.getNama_anggota(),
                anggota.getNim_anggota(),
                anggota.getId_minat(),
                anggota.getAngkatan_anggota(),
                anggota.getId_anggota());
                return "redirect:/admin";
                } catch (Exception e) {
                    // TODO: handle exception
                    model.addAttribute("invalidID", true);
                }
            model.addAttribute("invalidID", true);
            return "redirect:/add";
        
    }

    @GetMapping("/editanggota/{id_anggota}")
    public String edit(@PathVariable("id_anggota") String id_anggota, Model model) {
        String sql = "SELECT * FROM ANGGOTA WHERE ID_ANGGOTA = ?";
        cerc anggota = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(cerc.class), id_anggota);
        model.addAttribute("anggota", anggota);
        return "editanggota";
    }

    @PostMapping("/editanggota")
    public String edit(cerc anggota) {
        String sql = "UPDATE ANGGOTA SET nama_ANGGOTA = ?, nim_ANGGOTA = ?, angkatan_ANGGOTA = ?, ID_MINAT = ? WHERE ID_ANGGOTA = ?";
        jdbcTemplate.update(sql, anggota.getNama_anggota(), anggota.getNim_anggota(),
                anggota.getAngkatan_anggota(),
                anggota.getId_minat(), anggota.getId_anggota());
        String id = anggota.getId_minat();
        return "redirect:/indexdetailadmin/".concat(id);
    }

    @GetMapping("/editketua/{id_ketua}")
    public String editketua(@PathVariable("id_ketua") String id_ketua, Model model) {
        String sql = "SELECT * FROM KETUA WHERE ID_KETUA = ?";
        cerc ketua = jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(cerc.class), id_ketua);
        model.addAttribute("ketua", ketua);
        return "editketua";
    }

    @PostMapping("/editketua")
    public String editketua(cerc ketua) {
        String sql = "UPDATE KETUA SET NAMA_KETUA = ?, ANGKATAN_KETUA = ?, NIM_KETUA = ? WHERE ID_KETUA = ?";
        jdbcTemplate.update(sql, ketua.getNama_ketua(),
                ketua.getAngkatan_ketua(), ketua.getNim_ketua(),
                ketua.getId_ketua());
        return "redirect:/admin";
    }

    @GetMapping("/harddelete/{id_anggota}")
    public String harddelete(@PathVariable("id_anggota") String id_anggota) {
        String sql = "DELETE ANGGOTA WHERE ID_ANGGOTA = ?";
        jdbcTemplate.update(sql, id_anggota);
        return "redirect:/admin";
    }
    
    @GetMapping("/trash")
    public String trash(Model model) {
        String sql = "SELECT * FROM ANGGOTA WHERE DELETED = 'y'";
        List<cerc> deletedList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(cerc.class));
        model.addAttribute("deletedlist", deletedList);
        return "trash";
    }

    @GetMapping("/softdelete/{id_anggota}")
    public String softdelete(@PathVariable("id_anggota") String id_anggota) {
        String sql = "UPDATE ANGGOTA SET DELETED = 'y' WHERE ID_ANGGOTA = ?";
        jdbcTemplate.update(sql, id_anggota);
        return "redirect:/admin";
    }

    @GetMapping("/restore/{id_anggota}")
    public String restore(@PathVariable("id_anggota") String id_anggota) {
        String sql = "UPDATE ANGGOTA SET DELETED = 'n' WHERE ID_ANGGOTA = ?";
        jdbcTemplate.update(sql, id_anggota);
        return "redirect:/admin";
    }

    @GetMapping("/indexdetail/{id_minat}")
    public String indexdetails(@PathVariable("id_minat") String id_minat, Model model) {
        String sql = "SELECT * FROM ANGGOTA WHERE ID_MINAT=? AND DELETED='n'";
        List<cerc> detailList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(cerc.class), id_minat);
        model.addAttribute("detaillist", detailList);
        return "indexdetail";
    }

    @GetMapping("/indexdetailadmin/{id_minat}")
    public String indexdetailadmin(@PathVariable("id_minat") String id_minat, Model model) {
        String sql = "SELECT * FROM ANGGOTA WHERE ID_MINAT=?  AND DELETED='n'";
        List<cerc> detailList = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(cerc.class), id_minat);
        model.addAttribute("detaillist", detailList);
        return "indexdetailadmin";
    }

    @RequestMapping("/searchid")
    public String hasilsearch(@PathParam("id_search") String id_search, Model model) {
        String sql = "SELECT * FROM ANGGOTA WHERE LOWER (NAMA_ANGGOTA) LIKE CONCAT(CONCAT ('%', ?), '%')";
        List<cerc> detailSearch = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(cerc.class), id_search);
        model.addAttribute("detailsearch", detailSearch);
        return ("searchid");
    }

    @RequestMapping("/searchidadmin")
    public String hasilsearchadmin(@PathParam("id_search") String id_search, Model model) {
        String sql = "SELECT * FROM ANGGOTA WHERE LOWER (NAMA_ANGGOTA) LIKE CONCAT(CONCAT ('%', ?), '%')";
        List<cerc> detailSearch = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(cerc.class), id_search);
        model.addAttribute("detailsearch", detailSearch);
        return ("searchid");
    }


}

