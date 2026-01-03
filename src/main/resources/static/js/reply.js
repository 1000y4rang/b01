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

// 댓글 등록
async function addReply(registerObj)
{
    const response = await axios.post(`/replies/`, registerObj);
    return response.data;
}

// 댓글 조회
async function getReply(rno)
{
    const response = await axios.get(`/replies/${rno}`);
    return response.data;
}

// 댓글 수정
async function modifyReply(registerObj)
{
    const response = await axios.put(`/replies/${registerObj.rno}`, registerObj);
    return response.data;
}


// 댓글 삭제
async function removeReply(rno)
{
    const response = await axios.delete(`/replies/${rno}`);
    return response.data;
}
