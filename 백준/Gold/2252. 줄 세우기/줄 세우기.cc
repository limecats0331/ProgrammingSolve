#include "bits/stdc++.h"

using namespace std;

int N, M;
int conn[32001];
vector<vector<int>> students;


int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> N >> M;
    for (int i = 0; i <= N; ++i) {
        students.push_back(vector<int>());
    }

    for (int i = 0; i < M; ++i) {
        int a, b;
        cin >> a >> b;
        students[a].push_back(b);
        conn[b]++;
    }

    queue<int> st;
    for (int i = 1; i <= N; ++i) {
        if(conn[i] == 0){
            st.push(i);
        }
    }

    while(!st.empty()){
        int now = st.front();
        cout << now << " ";
        st.pop();

        for(int student : students[now]){
            conn[student]--;
            if(conn[student] == 0){
                st.push(student);
            }
        }
    }


    return 0;
}
