package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {

                    ArtSpaceMain()

                }
            }
        }
    }
}


@Composable
fun ArtSpaceMain(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(10.dp))
        ButtonFunction()

    }
}

@Composable
fun ArtSpaceImage(image : Int, titre : String, auteur :String){

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .size(350.dp)
                .shadow(10.dp)
                .background(Color.White)
                .border(BorderStroke(width = 3.dp, Color.Black), shape = RectangleShape)
                .padding(20.dp),

            painter = painterResource(image),
            contentDescription = null)

    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)

    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),

            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                modifier = Modifier.padding(5.dp),
                text = titre,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = auteur,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }

}



@Composable
fun ButtonFunction(){

// Avec liste

    val artList = listOf(
        ArtSpaceList(R.drawable.anime_8937913_640, "Homme avec cheveux blanc", "AI libre de droit 2025"),
        ArtSpaceList(R.drawable.ai_generated_8610368_640, "Manga Femme", "AI libre de droit"),
        ArtSpaceList(R.drawable.ai_generated_9097044_640, "Combattante", "AI FreePik libre de droit 2025"),
        ArtSpaceList(R.drawable.ai_generated_8402016_640, "Cyber Punk avec Tatouage", "AI libre de droit 2025"),
        ArtSpaceList(R.drawable.anime_8655210_640, "Agent Entretien", "AI :-) libre de droit 2025")
    )

    var state by remember { mutableIntStateOf(0) }

    ArtSpaceImage(
        image = artList[state].imageRes,
        titre = artList[state].title,
        auteur = artList[state].author
    )

//    when(state){
//        1 -> ArtSpaceImage(
//            R.drawable.ai_generated_8610368_640,
//            titre = "Manga Femme",
//            auteur = "AI libre de droit"
//        )
//        2 -> ArtSpaceImage(
//            R.drawable.ai_generated_9097044_640,
//            titre = "Combattante",
//            auteur = "AI FreePik libre de droit 2025 "
//        )
//        3 -> ArtSpaceImage(
//            R.drawable.ai_generated_8402016_640,
//            titre = "Cyber Punk avec Tatouage",
//            auteur = "AI libre de droit 2025"
//        )
//        4 -> ArtSpaceImage(
//            R.drawable.anime_8655210_640,
//            titre = "Agent Entretien",
//            auteur = "AI :-) libre de droit 2025"
//        ) else -> ArtSpaceImage(
//        R.drawable.anime_8937913_640,
//        titre = "Homme avec cheveux blanc",
//        auteur = "AI libre de droit 2025")
//    }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            modifier = Modifier
                .width(120.dp)
                .height(40.dp),
            onClick = {
                if (state > 0)
                state--
            }) {
            Text("Previous")
        }

        Button(
            modifier = Modifier
                .width(120.dp)
                .height(40.dp),
            onClick = {
                if (state < artList.lastIndex ) // On modifie l'accés a la list pour eviter le débordement
                state++                         // Avec lastIndex ou size -1
            }) {
            Text("Next")
        }
    }
}

data class ArtSpaceList(val imageRes: Int, val title: String, val author: String)


@Preview(showBackground = true)
@Composable
fun PreviewApp(){
    ArtSpaceTheme {
        ArtSpaceMain()
    }
}