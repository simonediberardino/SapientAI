package com.dibgiorgi.sapientai.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.dibgiorgi.sapientai.data.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

// La classe ChatViewModel estende ViewModel, una classe che è parte dell'architettura di Android
// e serve a separare la logica dalla UI. Il ViewModel persiste durante la durata
// di un'attività o un frammento, quindi è ideale per mantenere lo stato anche durante
// la rotazione dello schermo o altre modifiche di configurazione.
// Non è strettamente necessario separare la logica dalla interfaccia (e quindi usare un viewmodel),
// (si potrebbe gestire tutto nel Compose) ma è estremamente consigliato per rendere l'applicazione
// più scalabile e facile da gestire.
class ChatViewModel : ViewModel() {

    // `state` è una variabile di tipo MutableStateFlow, che è una versione mutabile
    // di un flusso di dati che può essere osservato. In altre parole, permette di mantenere e
    // aggiornare lo stato della chat. Questo stato è rappresentato da una lista mutabile di messaggi.
    // `MutableStateFlow` è ideale per l'uso con Jetpack Compose, poiché Compose si aggiorna automaticamente
    // ogni volta che lo stato cambia.
    private val state: MutableStateFlow<MutableList<ChatMessage>> = MutableStateFlow(mutableListOf())

    // La funzione `input` simula l'invio di un messaggio nella chat.
    // Questa funzione è chiamata dal composable (UI) ogni volta che l'utente inserisce un messaggio.
    // L'architettura ViewModel permette di eseguire l'elaborazione dei dati in modo separato dalla UI,
    // rendendo più facile testare e gestire lo stato.
    fun input(message: String) {
        // Qui, aggiorniamo lo stato aggiungendo un nuovo messaggio alla lista di chat.
        // `state.update` permette di aggiornare il valore di `state`
        // e notifica la UI che c'è stato un cambiamento.
        state.update {
            // Aggiungiamo un nuovo oggetto `ChatMessage` alla lista.
            // In questo caso, `ChatMessage` è un semplice modello che contiene il mittente e il testo del messaggio.
            it.add(ChatMessage("You", message))
            it // ritorniamo la lista aggiornata
        }

        // A questo punto, il messaggio è stato aggiunto alla lista e la UI viene aggiornata in modo automatico.
        // Poiché `state` è un `MutableStateFlow`, qualsiasi composable che osserva `state`
        // riceverà l'aggiornamento e mostrerà i nuovi messaggi.
        // Nota bene: Non è stata ancora gestita la visualizzazione dei messaggi, quindi l'interfaccia grafica ancora non viene
        // aggiornata quando viene inviato un messaggio

        // Qui è dove sarebbe logico inviare la richiesta al backend (ad esempio OpenAI)
        // per ottenere una risposta alla chat. Ad esempio, potresti inviare il messaggio
        // e poi aggiornare lo stato con la risposta dell'AI, ma questo codice è lasciato
        // come TODO in attesa di implementazione.
        println(state.value) // Stampa lo stato corrente della chat (per il debug).
    }
}
