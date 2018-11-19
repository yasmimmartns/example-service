package com.example.restservice;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class RestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestserviceApplication.class, args);
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Cidade {
	private UUID idCidade;
	private String Nome;
	private Estado estado;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Estado {
	private UUID idEstado;
	private String Nome;
}

@RestController
class CidadeDAO {

	@PostMapping("/cidade")
    public Cidade create(@RequestBody Cidade cidade){
        cidade.setIdCidade(UUID.randomUUID());
        return cidade;
    }

    @GetMapping("/cidade")
    public List<Cidade> read(){
        return Stream.of(
            new Cidade(UUID.randomUUID(), "Americana", new Estado(UUID.randomUUID(), "Parana"))
        ).collect(Collectors.toList());
    }

    @PutMapping ("/cidade")
    public Cidade update(@RequestBody Cidade cidade){
        cidade.setNome(cidade.getNome());
        return cidade;
    }

    @DeleteMapping ("/cidade/{id}")
    public int delete(@PathVariable Long id){
        return 200;
    }
}

