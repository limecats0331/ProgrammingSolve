#include <string>
#include <vector>

using namespace std;
#define ll long long

vector<ll> solution(int x, int n) {
    vector<ll> answer;
    
	for(int i=1;i<=n;++i){
        answer.push_back(x*i);
    }
    
    return answer;
}