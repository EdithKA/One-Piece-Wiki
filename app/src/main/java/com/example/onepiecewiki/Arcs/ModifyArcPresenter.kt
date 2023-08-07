import com.example.onepiecewiki.Arcs.Arc_data
import com.example.onepiecewiki.Arcs.ModifyArcActivity
import com.example.onepiecewiki.Model

class ModifyArcPresenter(private val view: ModifyArcActivity) {

    private val model = Model(view)

    private var arc: Arc_data? = null
    var isFavorite: String = "No" // Inicialmente "No"

    fun onViewCreated(arc: Arc_data?) {
        this.arc = arc

        arc?.let {
            view.showArcDetails(it.arc_title, it.arc_episode, it.arc_chapters, it.arc_volume, it.arc_favorite)
            checkIfArcIsFavorite(it.arc_favorite)
        }
    }

    fun save(title: String, episodes: String, chapters: String, volumes: String, favorite: String) {
        val updatedArc = Arc_data(
            id = arc?.id ?: 0,
            arc_number = arc?.arc_number ?: "",
            arc_title = title,
            arc_chapters = chapters,
            arc_volume = volumes,
            arc_episode = episodes,
            arc_favorite = isFavorite // Pasamos el valor actual de isArcFavorite
        )

        model.updateArc(updatedArc) {
            view.showMessage("Arco actualizado correctamente.")
            view.returnMainActivity()
        }
    }

    fun addFavorite() {
        isFavorite = if (isFavorite == "No") "Sí" else "No" // Cambiamos el valor de "Sí" a "No" o viceversa
    }

    fun deleteArc() {
        arc?.let { arc ->
            model.deleteArc(arc) {
                view.showMessage("Arco eliminado correctamente.")
                view.returnMainActivity()
            }
        }
    }

    private fun checkIfArcIsFavorite(arc_favorite: String) {
        isFavorite = arc_favorite
        view.setFavoriteState(isFavorite)
    }
}
