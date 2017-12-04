package work.johntsai.kotgank

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_meizi.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import work.johntsai.kotgank.common.inflate
import work.johntsai.kotgank.model.GankDataModel
import work.johntsai.kotgank.model.GankDataModelWrapper
import work.johntsai.kotgank.model.GankDataType

class MeiziFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_meizi)
        view?.let {
            val recyclerView = view.recyclerView as RecyclerView
            val gankService = RetrofitClient().getGankService()
            gankService?.getGankDataByType(GankDataType.WELFARE.value, 10, 1)
                    ?.enqueue(object : Callback<GankDataModelWrapper<GankDataModel>> {
                        override fun onFailure(call: Call<GankDataModelWrapper<GankDataModel>>?, t: Throwable?) {
                        }

                        override fun onResponse(call: Call<GankDataModelWrapper<GankDataModel>>?, response: Response<GankDataModelWrapper<GankDataModel>>?) {
                            val datas = response?.body()?.results
                            recyclerView.adapter = MeiziRecyclerViewAdapter(datas, context)
                        }
                    })

        }
        return view
    }
}