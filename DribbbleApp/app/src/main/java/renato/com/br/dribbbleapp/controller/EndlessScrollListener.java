package renato.com.br.dribbbleapp.controller;
//https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews

import android.widget.AbsListView;

public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    // The minimum amount of items to have below your current scroll position
    // before loading more.

    //A quantidade mínima de itens para ter abaixo de sua posição de rolagem atual
    // Antes de colocar mais.
    private int visibleThreshold = 4;

    // The current offset index of data you have loaded
    //O índice atual de deslocamento de dados que você carregou
    private int currentPage = 0;

    // The total number of items in the dataset after the last load
    //O número total de itens no conjunto de dados após a última carga
    private int previousTotalItemCount = 0;

    // True if we are still waiting for the last set of data to load.
    //Verdadeiro se ainda estamos à espera para o último conjunto de dados para carregar
    private boolean loading = true;

    // Sets the starting page index
    //Define o índice da página inicial
    private int startingPageIndex = 0;

    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public EndlessScrollListener(int visibleThreshold, int startPage) {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.

    // Isso acontece muitas vezes por segundo durante um Scroll, por isso muito cuidado com o código que você colocar aqui.
    // Recebemos alguns parâmetros úteis para nos ajudar a descobrir se precisamos carregar mais alguns dados,
    // Mas primeiro vamos verificar se estamos à espera para a carga anterior para terminar.
    @Override
    public void onScroll(AbsListView view,int firstVisibleItem,int visibleItemCount,int totalItemCount)
    {
        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        // Se a contagem total de item é igual a zero e o anterior não é, assumir que a
        // Lista é invalidada e deve ser reposta volta ao estado inicial
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) { this.loading = true; }
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        // Se ele ainda está carregando, vamos verificar para ver se a contagem de conjunto de dados tem
        // Alterado, se assim podemos concluir que terminar de carregar e atualizar a página atual
        // Número e contagem de itens totais.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // Se ele não está a carregar, vamos verificar para ver se a gente tiver violado
        // O visibleThreshold e precisa recarregar mais dados.
        // Se precisa recarregar mais alguns dados, executamos onLoadMore para buscar os dados.
        if (!loading && (totalItemCount - visibleItemCount)<=(firstVisibleItem + visibleThreshold)) {
            onLoadMore(currentPage + 1, totalItemCount);
            loading = true;
        }
    }

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't take any action on changed
    }
}