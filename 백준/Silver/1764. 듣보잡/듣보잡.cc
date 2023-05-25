#include "bits/stdc++.h"

using namespace std;

int N, M;
unordered_set<string> dont_heard;

int main() {
    cin.tie(0);
    ios::sync_with_stdio(0);

    cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        string name;
        cin >> name;
        dont_heard.insert(name);
    }

    int cnt;
    vector<string> answer;
    for (int i = 0; i < M; ++i) {
        string name;
        cin >> name;
        if(dont_heard.find(name) != dont_heard.end()){
            answer.push_back(name);
            cnt++;
        }
    }

    sort(answer.begin(), answer.end());
    cout << cnt << "\n";
    for(string name : answer){
        cout << name << "\n";
    }

    return 0;
}

