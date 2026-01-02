async function get1(bno)
{
    const result = await axios.get(`/replies/list/${bno}`);
    //console.log(result.data);

    return result.data;
}

async function getList({bno, page, size, goLast})
{
    // 조회
    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}});

    // 재조회 (최신순 조회)
    if(goLast)
    {
        const total = result.data.total;
        const lastPage = parseInt(Math.ceil(total / size));
        return getList({bno:bno, page:lastPage, size:size});
    }

    return result.data;
}