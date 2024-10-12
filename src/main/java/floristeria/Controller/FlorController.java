package floristeria.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import floristeria.Model.Flor;
import floristeria.Services.FlorService;


@Controller
@RequestMapping(value = "/flores")
public class FlorController {  

    @Autowired
    private FlorService florService;


    @GetMapping("/listar")
    public String listarFlores(Model model) {
        List<Flor> flores = florService.listarTodas();  // Cambiar a listarTodas()
        model.addAttribute("flores", flores);
        return "flores";  // Retorna la vista "flores"
    }

    @GetMapping("/registrar")
    public String registrarFlores(Model model) {
        model.addAttribute("flor", new Flor());  // Inicializa un nuevo objeto Flor
        return "regflores";  // Retorna la vista "regflores"
    }

    @PostMapping("/guardar")
    public String guardarFlor(@ModelAttribute Flor flor) {
        florService.save(flor);  // Guarda la nueva flor
        return "redirect:/flores/listar";  // Redirige a la lista de flores
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFlor(@PathVariable Long id) {
        florService.deleteById(id);  // Elimina la flor por ID
        return "redirect:/flores/listar";  // Redirige a la lista de flores
    }

    @GetMapping("/editar/{id}")
    public String editarFlor(@PathVariable Long id, Model model) {
        Flor flor = florService.findById(id);  // Busca la flor por ID
        model.addAttribute("flor", flor);  // Agrega la flor al modelo

        return "editflor";
    }

    @PostMapping("/actualizar")
    public String actualizarFlor(@ModelAttribute Flor flor) {
    Flor existingFlor = florService.findById(flor.getId());
    if (existingFlor != null) {
        existingFlor.setNombre(flor.getNombre());
        existingFlor.setColor(flor.getColor());
        existingFlor.setVariedad(flor.getVariedad());
        existingFlor.setCantidad(flor.getCantidad());
        existingFlor.setPrecioCompra(flor.getPrecioCompra());
        existingFlor.setPrecioVenta(flor.getPrecioVenta());
        florService.update(existingFlor);
    }
    return "redirect:/flores/listar";
}

}